package com.example.BridgeAndCoCursach.Service;

import com.example.BridgeAndCoCursach.APIRepository.PathingAPIRepository;
import com.example.BridgeAndCoCursach.Models.Pathing;
import com.example.BridgeAndCoCursach.Models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PathingServiceImp implements PathingService{
    @Autowired
    PathingAPIRepository pathingAPIRepository;
    @Override
    public Optional<Pathing> update(Long Id, Pathing Request) {
        Optional<Pathing> pathing = pathingAPIRepository.findById(Id);
        if (pathing.isEmpty()){
            return null;
        }
        else {
            pathing.get().setPath_time(Request.getPath_time());
            pathing.get().setAdress(Request.getAdress());
            pathing.get().setTransport(Request.getTransport());
            pathing.get().setPathcost(Request.getPathcost());
            pathingAPIRepository.save(pathing.get());
            return pathing;
        }
    }

    @Override
    public Pathing getASingle(Long Id) {
        return pathingAPIRepository.findById(Id).orElseThrow();
    }

    @Override
    public List<Pathing> getAll() {
        return pathingAPIRepository.findAll();
    }
}
