package com.nttdata.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.application.BuscarPorTipoNumeroDocumentoUseCase;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")
public class ClienteController {

	private BuscarPorTipoNumeroDocumentoUseCase buscarPorTipoNumeroDocumentoUseCase;

	ClienteController() {
		this.buscarPorTipoNumeroDocumentoUseCase = new BuscarPorTipoNumeroDocumentoUseCase();
	}

	@GetMapping("/informacion")
	public ResponseEntity<?> obtenerInformacionCliente(@RequestParam String tipoDocumento,
			@RequestParam String numeroDocumento) {

		return this.buscarPorTipoNumeroDocumentoUseCase.run(tipoDocumento, numeroDocumento);

	}
}
