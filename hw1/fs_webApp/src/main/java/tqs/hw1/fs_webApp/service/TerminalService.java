package tqs.hw1.fs_webApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tqs.hw1.fs_webApp.data.entity.Terminal;
import tqs.hw1.fs_webApp.data.repository.TerminalRepository;

@Service
public class TerminalService {

    @Autowired
    TerminalRepository repo;

    public List<Terminal> getAllTerminals(){
        return repo.findAll();
    }
}
