package pe.com.empresa.rk.service.impl;

import org.springframework.stereotype.Service;
import pe.com.empresa.rk.service.TestService;

/**
 * Created by josediaz on 10/10/2016.
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public String test() {
        return "Hello, World!";
    }
}
