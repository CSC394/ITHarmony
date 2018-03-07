package com.itharmony.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.itharmony.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.itharmony.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.itharmony.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.UserProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.UserProfile.class.getName() + ".candidateEducations", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.UserProfile.class.getName() + ".candidateWorkExperiences", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.UserProfile.class.getName() + ".skillsProfiles", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.UserProfile.class.getName() + ".jobPosts", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.UserProfile.class.getName() + ".jobMatches", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.CandidateProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.CompanyProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.CultureProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.SkillsProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.JobPost.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.JobPost.class.getName() + ".jobMatches", jcacheConfiguration);
            cm.createCache(com.itharmony.domain.CandidateEducation.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.CandidateWorkExperience.class.getName(), jcacheConfiguration);
            cm.createCache(com.itharmony.domain.JobMatch.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
