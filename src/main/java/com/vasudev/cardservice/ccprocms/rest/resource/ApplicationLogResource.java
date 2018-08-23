package com.vasudev.cardservice.ccprocms.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vasudev.cardservice.ccprocms.rest.resource.logvm.LoggerVM;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import io.micrometer.core.annotation.Timed;

/**
 * Controller for view and managing Log Level at runtime.
 *
 */
@RestController
@RequestMapping("/management")
public class ApplicationLogResource {

	@GetMapping("/logs")
	@Timed
	public List<LoggerVM> getList() {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		return context.getLoggerList().stream().map(LoggerVM::new).collect(Collectors.toList());
	}

	@PutMapping("/logs")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Timed
	public void changeLevel(@RequestBody LoggerVM jsonLogger) {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		context.getLogger(jsonLogger.getName()).setLevel(Level.valueOf(jsonLogger.getLevel()));
	}
}
