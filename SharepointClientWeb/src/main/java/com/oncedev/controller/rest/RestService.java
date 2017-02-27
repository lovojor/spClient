/**
 * 
 */
package com.oncedev.controller.rest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oncedev.model.PairData;
import com.oncedev.model.RqFile;
import com.oncedev.service.SharePointService;

/**
 * @author lovojor
 *
 */
@RestController
public class RestService {

	private static final Logger log = LoggerFactory.getLogger(RestService.class);

	@Autowired
	private SharePointService spService;

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody List<PairData> getAll() {
		List<PairData> result = new ArrayList<>();
		try {
			String responseBody = spService.performHttpRequest(HttpMethod.GET,
					"/_api/web/lists/getbytitle('TestCarlos')");
			log.debug(responseBody);
			result.add(new PairData("response", responseBody));
		} catch (Exception e) {
			log.error("Error inicial: " + e.getMessage());
			result.add(new PairData("Error", e.getMessage()));
		}
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody List<PairData> Save(@RequestBody RqFile rq) {
		List<PairData> result = new ArrayList<>();
		try {
			byte[] file = Files.readAllBytes(Paths.get(rq.getPathFile()));
			String responseBody = spService.attachFile(rq.getPathSp(), file);
			log.debug(responseBody);
			result.add(new PairData("response", responseBody));
		} catch (Exception e) {
			log.error("Error inicial: " + e.getMessage());
			result.add(new PairData("Error", e.getMessage()));
		}
		return result;
	}

}