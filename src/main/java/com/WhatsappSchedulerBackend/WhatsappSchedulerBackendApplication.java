package com.WhatsappSchedulerBackend;

import com.WhatsappSchedulerBackend.thread.MainThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ComponentScan
@SpringBootApplication
public class WhatsappSchedulerBackendApplication
{

	@Autowired
	MainThread thread ;

	ConfigurableApplicationContext context;

	static ExecutorService threadeExecutor = Executors.newCachedThreadPool();
	public static void main( String[] args )
	{
		WhatsappSchedulerBackendApplication app = new WhatsappSchedulerBackendApplication();

		app.context = SpringApplication.run(WhatsappSchedulerBackendApplication.class, args);

		// thread.run();
		app.thread = (MainThread) app.context.getBean("thread");
		threadeExecutor.execute(app.thread);
	}
}
