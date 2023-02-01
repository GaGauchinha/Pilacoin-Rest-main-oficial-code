package br.ufsm.politecnico.csi.tapw.pila.JobRunr;

import br.ufsm.politecnico.csi.tapw.pila.servidor.service.MineracaoService;
import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//https://www.baeldung.com/java-jobrunr-spring
@Component
public class Time {

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired(required = false)
    private MineracaoService mineracaoService;

    @Job
    public void processOnce(boolean minerar) {
        jobScheduler.enqueue(() -> mineracaoService.initPilacoin(minerar));
    }

    @Job(name = "Minerar pila")
    public void processScheduledRecurrently(boolean minerar) {
        jobScheduler.scheduleRecurrently(Cron.minutely(), () -> mineracaoService.initPilacoin(minerar));
    }
}
