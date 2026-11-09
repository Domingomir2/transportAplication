package com.transport.transportApplication;

import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TransportApplicationTests {

	@Autowired
	private MeterRegistry meterRegistry;

	@Test
	void contextLoads() {
		assertNotNull(meterRegistry);
	}

}
