package altamirano.hernandez.springboot3_firebase.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class HomeController {
    @GetMapping("")
    public ResponseEntity<?> home() {
        Map<String, Object> json = new HashMap<>();
        json.put("msg", "Funcionando homeController");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
