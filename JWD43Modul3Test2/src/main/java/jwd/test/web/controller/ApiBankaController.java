package jwd.test.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.test.model.Banka;
import jwd.test.model.TipRacuna;
import jwd.test.service.BankaService;
import jwd.test.service.TipRacunaService;
import jwd.test.support.BankaToBankaDTO;
import jwd.test.support.TipRacunToTipRacunaDTO;
import jwd.test.web.dto.BankaDTO;
import jwd.test.web.dto.TipRacunaDTO;

@RestController
@RequestMapping(value = "/api/banke")
public class ApiBankaController {

	@Autowired
	private BankaService bankaService;
	
	@Autowired
	private TipRacunaService tipRacunaService;

	@Autowired
	private BankaToBankaDTO toDto;
	
	@Autowired
	private TipRacunToTipRacunaDTO model3ToDto;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BankaDTO>> get() {
		List<Banka> model2List = bankaService.findAll();
		return new ResponseEntity<List<BankaDTO>>(toDto.convert(model2List), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<BankaDTO> getOne(@PathVariable Long id) {
		Banka banka = bankaService.findOne(id);
		if (banka == null) {
			return new ResponseEntity<BankaDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BankaDTO>(toDto.convert(banka), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/tipovi-racuna")
	public ResponseEntity<List<TipRacunaDTO>> getTipovi(@PathVariable Long id) {
		List<TipRacuna> tipoviRacuna = tipRacunaService.findByBankaId(id);
		return new ResponseEntity<List<TipRacunaDTO>>(model3ToDto.convert(tipoviRacuna), HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}