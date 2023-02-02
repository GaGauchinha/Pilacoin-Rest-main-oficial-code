package br.ufsm.politecnico.csi.tapw.pila.controller;

import br.ufsm.politecnico.csi.tapw.pila.JobRunr.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "/pilacoin")
public class PilacoinController {
        @Autowired
        Time time;

        @GetMapping("/minerar")
        public void scheduleRecurrently() {
            boolean minerar = true;
            time.processOnce(minerar);
        }

    }
