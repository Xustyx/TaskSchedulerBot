package com.xsty.telegram.alert;

import com.xsty.scheduler.common.ObservableCrudRepositoryService;
import org.springframework.stereotype.Component;

/**
 * Created by XST on 22/10/2017.
 */
@Component("alertService")
public class AlertService extends ObservableCrudRepositoryService<Alert, Long> {

    public AlertService(AlertRepository repository) {
        super(repository);
    }
}
