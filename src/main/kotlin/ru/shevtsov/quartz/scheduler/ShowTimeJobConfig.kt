package ru.shevtsov.quartz.scheduler

import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ShowTimeJobConfig(
    val schedulerProperties: SchedulerProperties
) {

    @Bean
    fun showTimeJobDetail(): JobDetail = JobBuilder
        .newJob(ShowTimeJob::class.java)
        .withIdentity("testJob", schedulerProperties.permanentJobsGroupName)
        .storeDurably()
        .requestRecovery(true)
        .build()

    @Bean
    fun showTimeTrigger(): Trigger = TriggerBuilder.newTrigger()
        .forJob(showTimeJobDetail())
        .withIdentity("testJobTrigger", schedulerProperties.permanentJobsGroupName)
        .withSchedule(CronScheduleBuilder.cronSchedule(schedulerProperties.showTimeJobCron))
        .build()
}