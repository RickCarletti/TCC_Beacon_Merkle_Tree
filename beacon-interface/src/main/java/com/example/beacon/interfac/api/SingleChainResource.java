package com.example.beacon.interfac.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.beacon.interfac.api.dto.PulseDto;
import com.example.beacon.interfac.domain.service.QuerySinglePulsesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(value = "/beacon/2.0/chain/{chainIndex}/pulse", produces= MediaType.APPLICATION_JSON_VALUE)
public class SingleChainResource {

    private final QuerySinglePulsesService singlePulsesService;

    @Autowired
    public SingleChainResource(QuerySinglePulsesService singlePulsesService) {
        this.singlePulsesService = singlePulsesService;
    }

    @GetMapping("/first")
    @ResponseBody
    public ResponseEntity first(@PathVariable Long chainIndex){
        PulseDto pulseDto = singlePulsesService.firstDto(chainIndex);

        if (pulseDto==null){
            return new ResponseEntity("Pulse Not Available.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(pulseDto, HttpStatus.OK);
    }

    @GetMapping("/last")
    @ResponseBody
    public ResponseEntity last(@PathVariable Long chainIndex){
        try {
            PulseDto pulseDto = singlePulsesService.lastDto(chainIndex);

            if (pulseDto==null){
                return new ResponseEntity("Pulse Not Available.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(pulseDto, HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{pulseIndex}")
    @ResponseBody
    public ResponseEntity chainAndPulse(@PathVariable Long chainIndex, @PathVariable Long pulseIndex){
        try {
            PulseDto pulseDto = singlePulsesService.findByChainAndPulseId(chainIndex, pulseIndex);

            if (pulseDto==null){
                return new ResponseEntity("Pulse Not Available.", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity(pulseDto, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
