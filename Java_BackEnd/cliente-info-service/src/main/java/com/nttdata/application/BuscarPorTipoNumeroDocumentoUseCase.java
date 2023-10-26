package com.nttdata.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.domain.Cliente;

public class BuscarPorTipoNumeroDocumentoUseCase {
	private static final Logger LOGGER = LoggerFactory.getLogger(BuscarPorTipoNumeroDocumentoUseCase.class);

	public ResponseEntity<?> run(String tipoDocumento, String numeroDocumento) {

		LOGGER.info("Tipo de documento ingresado:" + tipoDocumento);
		LOGGER.info("Número de documento ingresado:" + numeroDocumento);

		try {
			if (!("C".equals(tipoDocumento) || "P".equals(tipoDocumento))) {
				LOGGER.warn("Tipo de documento no válido:" + tipoDocumento);
				return ResponseEntity.badRequest().body("Tipo de documento no válido.");
			}
			int ndocumento = Integer.parseInt(numeroDocumento);
			if (ndocumento != 23445322 || !"C".equals(tipoDocumento)) {
				LOGGER.warn("Cliente no válido con tipo de documento " + tipoDocumento + " y número de documento "
						+ numeroDocumento);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
			}

			Cliente cliente = new Cliente();
			// Mockear datos del cliente
			cliente.mockearCliente(ndocumento, tipoDocumento);

			LOGGER.info("Cliente encontrado");
			return ResponseEntity.ok(cliente);

		} catch (Exception e) {
			LOGGER.error("Ocurrió un error interno en el servidor. \n" + e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Ocurrió un error interno en el servidor. \n" + e);
		}
	}

}
