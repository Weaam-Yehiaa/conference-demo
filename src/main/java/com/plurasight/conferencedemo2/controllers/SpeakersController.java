package com.plurasight.conferencedemo2.controllers;

import com.plurasight.conferencedemo2.models.Session;
import com.plurasight.conferencedemo2.models.Speaker;
import com.plurasight.conferencedemo2.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    @Autowired // gives me CRUD access and other access to my Speakers database table and data
    private SpeakerRepository speakerRepository;

    @GetMapping
    private List<Speaker>list()
    {   // will return using GET HTTP verb
        return speakerRepository.findAll();
    }
    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id)
    {
        return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker create(@RequestBody Speaker speaker)
    {
        return speakerRepository.saveAndFlush(speaker);
    }
    @RequestMapping(value = {"id"},method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id)
    {
        // also need to check for children records before delete
        speakerRepository.deleteById(id);
    }
    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker)
    {
        //because this is PUT, we expect all attributes to be passed in A PATCH would need only
        // TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Speaker existingSpeaker=speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"session_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

}
