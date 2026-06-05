package com.cristiandev.standard_project.router.cliente.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.cristiandev.standard_project.router.cliente.base.ClientRouterBase.*;

@RequestMapping(API_BASE_PATH)
public abstract class ClientRouterBase {

    public static final String API_BASE_PATH = "/api/v1/clientes";
    protected static final String SAVE = "/save";
    protected final Logger log = LoggerFactory.getLogger(getClass());
}
