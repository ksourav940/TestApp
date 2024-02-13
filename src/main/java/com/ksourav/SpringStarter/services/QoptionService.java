package com.ksourav.SpringStarter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksourav.SpringStarter.models.Qoption;
import com.ksourav.SpringStarter.repository.QoptionRepository;

@Service
public class QoptionService {

       @Autowired
    private QoptionRepository qoptionRepository;

    public Optional<Qoption> getById(long id){
        return qoptionRepository.findById(id);
    }

    public  List<Qoption> getAll(){

        return qoptionRepository.findAll();
    }

    public void delete(Qoption qoption){
        if(qoption != null){
            qoptionRepository.delete(qoption);
        }
      
    }

    public Qoption save(Qoption qoption){
        if(qoption != null){
            return qoptionRepository.save(qoption);
        }else
        return null;
    }

}
