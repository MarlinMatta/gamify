package edu.uapa.web.app.gamify.utils.bootstrap;

import edu.uapa.web.app.gamify.domains.securities.Parameter;
import edu.uapa.web.app.gamify.models.interfaces.BootStrapInsert;
import edu.uapa.web.app.gamify.services.securities.ParameterService;
import edu.uapa.web.app.gamify.utils.security.SystemParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasicParameter implements BootStrapInsert {

    private final ParameterService service;

    @Autowired
    public BasicParameter(ParameterService service) {
        this.service = service;
    }

    @Override
    public void insert() {
        for (Integer parameter : SystemParameters.getValues()) {
            Optional<Parameter> optional = service.findByCode(parameter);
            if (optional.isEmpty()) {
                Parameter param = new Parameter();
                param.setCode(parameter);
                param.setParamValueType(SystemParameters.type(parameter));
                param.setName(SystemParameters.name(parameter));
                param.setDescription(SystemParameters.description(parameter));
                param.setValue(SystemParameters.value(parameter));
                param.setChangeRoot(SystemParameters.changeRoot(parameter));
                service.bootStrap(param);
            }
        }
    }
}
