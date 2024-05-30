package com.country.lookup.service.persistence;

import com.country.lookup.service.config.UrlConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ScheduledDataSyncService {

    private final UrlConfig urlConfig;
    private final DataSyncService syncService;

    @PostConstruct
    public void init() {
        scheduledSync();
    }

    /**
     * Triggers synchronizing country calling codes database values with fresh data from URL stored in application properties.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledSync() {
        syncService.sync(urlConfig.getCountryCallingCodesTable());
    }

}
