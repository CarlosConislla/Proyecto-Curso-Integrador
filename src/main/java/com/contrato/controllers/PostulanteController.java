package com.contrato.controllers;

import com.contrato.models.Departamento;
import com.contrato.models.Postulante;
import com.contrato.repository.DepartamentoRepository;
import com.contrato.services.IUploadFileService;
import com.contrato.services.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.util.List;

@Controller
public class PostulanteController {

    @Autowired
    private PostulanteService postulanteService;

    @Autowired
    private IUploadFileService iUploadFileService;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @GetMapping("/postulantes")
    public String ListarPostulantes(Model model) {
        try {
            model.addAttribute("postulantesList", postulanteService.listAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "User/postulantes";
    }

    @GetMapping(value = "postulantes/uploads/{filename}")
    public ResponseEntity<Resource> goImage(@PathVariable String filename) {
        Resource resource = null;
        try {
            resource = iUploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/postulantes/nuevo")
    public String MostrarFormDePostulante(Model model) {
        List<Departamento> departamentoList = departamentoRepository.findAll();
        model.addAttribute("postulante", new Postulante());
        model.addAttribute("departamentoList", departamentoList);
        return "User/postulante_form";
    }

    @PostMapping("/postulantes/guardar")
    public String guardarPostulante(@Validated @ModelAttribute("postulante") Postulante postulante,
                                    BindingResult result, Model model, @RequestParam("file")MultipartFile cv,
                                    RedirectAttributes flash, SessionStatus status) throws Exception {
        if (result.hasErrors()){
            System.out.println(result.getFieldError());
            return "User/postulante_form";
        } else {
            if (!cv.isEmpty()){
                if (postulante.getId() > 0 && postulante.getCv() != null && postulante.getCv().length() > 0){
                    iUploadFileService.delete(postulante.getCv());
                }
                String uniqueFileName = iUploadFileService.copy(cv);
                postulante.setCv(uniqueFileName);
            }
            postulanteService.save(postulante);
            status.setComplete();
        }
        return "redirect:/postulantes";
    }

    @GetMapping("/postulantes/update/{id}")
    public String goUpdate (@PathVariable(value = "id") Integer id, Model model){
        Postulante postulante = postulanteService.listById(id);
        model.addAttribute("postulante",postulante);

        List<Departamento> departamentoList = departamentoRepository.findAll();
        model.addAttribute("departamentoList", departamentoList);

        return "User/postulante_form";
    }

    @GetMapping("/postulantes/detail/{id}")
    public String goDetail (@PathVariable(value = "id") Integer id, Model model){
        Postulante postulante = postulanteService.listById(id);
        model.addAttribute("postulante",postulante);

        List<Departamento> departamentoList = departamentoRepository.findAll();
        model.addAttribute("departamentoList", departamentoList);

        return "User/cv_detalles";
    }

    @GetMapping("/postulantes/delete/{id}")
    public String eliminarPostulante (@PathVariable(value = "id") Integer id, Model model){
        try {
            postulanteService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/postulantes";
    }
}
