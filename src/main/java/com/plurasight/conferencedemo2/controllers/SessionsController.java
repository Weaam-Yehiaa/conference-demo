package com.plurasight.conferencedemo2.controllers;

import com.plurasight.conferencedemo2.models.Session;
import com.plurasight.conferencedemo2.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// here we have all CRUD (CREATE DELETE UPDATE LIST GET)operations for a prepare REST


// make Spring MVC knows that are controllers
@RestController  // this will respond payloads incoming and outgoing as JSON REST endpoints
@RequestMapping("/api/v1/sessions")// tells the router what the mapping URL will look like
public class SessionsController {
    // use spring to inject or auto wire the sessions repository that we just created
    @Autowired
    private SessionRepository sessionRepository;
    // spring will auto write this when our SessionController is built it will create an instance of the session repository and put it onto our class
   @GetMapping
    public List<Session> list(){
       /* we will return list of sessions and Spring MVC will then pass that over to jackson which is the serialization library
       which will turn those sessions into JSON and return them back to the caller
       * */
        return sessionRepository.findAll();
    }

    // REST controllers in Spring MVC in the return types
    //this method is to get specific session by ID
    @GetMapping
    @RequestMapping("{id}")// t2rebn al fhmto hna byb3t id by7oto gnb "/api/v1/sessions" w yb2a da URL
    public Session get(@PathVariable Long id)
    {
        return sessionRepository.getOne(id);
    }
    /*
     By default, the REST controller will return 200s as the response status fo all the calls
    Even though we added @PostMapping to the endpoint , it's going to infer anything from that because typically
    when we create something or post something, you get a 201 back, but Sprig REST controllers just will return a 200
    So one way that you can override those would be added the @ResponseStatus we give HttpStatus a CREATED which maps to 201 in the
    HTTP world bs 34an da m4 course REST b 3omk hansebha 201 3ady
     */

    @PostMapping// we're requiring the HTTP verb POST to be presented with this API call
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@RequestBody final Session session)
    {
        return sessionRepository.saveAndFlush(session);
        // when we're using JPA and entities, u can save objects as you're working with it, but it actually doesn't
        // commit to db until u flush it soooo that method help u to save and flush all at once as u pass in JPA entities
    }
    // there's no DeleteMapping Like GetMapping it's only get and post
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE) // we also add her different HTTP method to this
    // so this requires the HTTP verb DELETE persented with API endpoints
    public void delete(@PathVariable Long id)
    {// also need to check for children records before delete
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)// changing the method to an HTTP verb PUT
    // in REST, when u updating a record you have to verbs that can u can choose from (PUT,PATCH)3la 7sb ana 3ayz a3ml implement ezay
    // bs PUT will typically replace all the attributes on the record that u r updating
    // bs PATCH will allow just a portion of the attributes to be updates
    public Session update(@PathVariable Long id,@RequestBody Session session)
    {
        //because this is PUT, we expect all attributes to be passed in A PATCH would need only
        // TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession=sessionRepository.getOne(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        // bt3ml copy ll existing in session w bt3ml ignore for third parameter
        return sessionRepository.saveAndFlush(existingSession);
        // here because we use PUT, we're expected all the attributes to br passed in. if they aren't all passed in
        // those attributes will be updates with null
    }
}
