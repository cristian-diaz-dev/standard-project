package com.cristiandev.standard_project.service;

import com.cristiandev.standard_project.dto.client.PlayerDTO;
import com.cristiandev.standard_project.exception.StandardProjectException;

public interface PlayerService {

    PlayerDTO save(PlayerDTO cliente) throws StandardProjectException;
}
