package co.inventorsoft.academyrest.controller;

import co.inventorsoft.academyrest.service.CustomerImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/customers/multipart")
@RequiredArgsConstructor
public class CustomerMultipartController {

    private final CustomerImportService customerImportService;

    @PostMapping
    public void importCustomers(@RequestParam MultipartFile file) {
        customerImportService.importCustomers(file);
    }
}
