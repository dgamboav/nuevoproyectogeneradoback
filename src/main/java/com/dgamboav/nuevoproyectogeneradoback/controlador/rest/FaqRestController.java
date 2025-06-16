package com.dgamboav.nuevoproyectogeneradoback.controlador.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PagedModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dgamboav.nuevoproyectogeneradoback.servicio.FaqService;
import com.dgamboav.nuevoproyectogeneradoback.dto.FaqDTO;

@Slf4j
@RestController
@RequestMapping("/api/faqs")
public class FaqRestController {
	
	private final FaqService faqService;
	
    FaqRestController(FaqService faqService){
        this.faqService = faqService;
    }

    @PostMapping
    public ResponseEntity<FaqDTO> crearFaq(@RequestBody FaqDTO faqDTO) {
		log.info("Iniciando la operación de crear Faq");
        FaqDTO createdFaq = faqService.crearFaq(faqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaq);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaqDTO> obtenerFaqPorId(@PathVariable Long id) {
		log.info("Iniciando la operación de obtener Faq {}", id);
        Optional<FaqDTO> faqDTO = faqService.obtenerFaqPorId(id);
        return faqDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Faq no encontrado"));
    }
	
	
	@GetMapping
    public ResponseEntity<PagedModel<FaqDTO>> obtenerTodosFaq(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Map<String, Object> filtros
    ) {
		log.info("Iniciando la operación de obtener listado de Faq");
        Pageable pageable = PageRequest.of(page, size);
        Page<FaqDTO> faqDTOPage = faqService.obtenerTodosFaq(pageable, filtros);
        return ResponseEntity.ok(new PagedModel<>(faqDTOPage));
    }
	
	@GetMapping("/todosMinimo")
     public ResponseEntity<List<FaqDTO>> obtenerTodosMinFaq() {
		 log.info("Iniciando la operación de obtener listado con valores minimos para reducir payload Faq");
         List<FaqDTO> faqDTOs = faqService.obtenerTodosMinFaq();
         return ResponseEntity.ok(faqDTOs);
     }

    @PutMapping("/{id}")
    public ResponseEntity<FaqDTO> actualizarFaq(@PathVariable Long id, @RequestBody FaqDTO faqDTO) {
		log.info("Iniciando la operación de actualizar Faq Id: {}", id);
        Optional<FaqDTO> updatedFaqDTO = faqService.actualizarFaq(id, faqDTO);
        return updatedFaqDTO.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Faq no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFaq(@PathVariable Long id) {
		log.info("Iniciando la operación de eliminar Faq Id: {}", id);
        faqService.eliminarFaq(id);
        return ResponseEntity.noContent().build();
    }
}