package com.jasper.rogers.ws;

import com.jasper.rogers.service.AdapterService;
import com.jasper.rogers.ws.client.cc.GetTerminalDetailsClient;
import com.jasper.rogers.ws.client.cc.JasperAPIClient;
import com.jasper.rogers.ws.exception.AdapterServiceException;
import com.jasperwireless.api.ws.schema.GetTerminalDetailsResponse;
import com.jasperwireless.api.ws.schema.TerminalType;
import com.rogers.wam.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapFaultException;
//import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;



@Endpoint

public class RogersTycoAdapterEndPoint {
    public static final String NAMESPACE_URI = "http://www.rogers.com/wam";
    public static final String QUERY_SUBSCRIBER_LOCAL_PART = "querySubscriber";
    public static final String ACTIVATE_SUBSCRIBER_LOCAL_PART  = "activateSubscriber";

    private static final Logger log = LogManager.getLogger();

    @Autowired
    private GetTerminalDetailsClient getTerminalDetailsClient;

    @Autowired
    private AdapterService adapterService;

    @Autowired
    private JasperAPIClient ccClient;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "querySubscriber")
    @ResponsePayload
    public QuerySubscriberResponse querySubscriber(@RequestPayload QuerySubscriber subscriber) throws Exception {
        log.debug("STARTED : querySubscriber().........." );
		log.debug("Trying out merge");
        log.debug("CTN=" + subscriber.getCtn() + "  TransactionId=" + subscriber.getTransactionId());

        QuerySubscriberResponse resp = adapterService.getQuerySubscriberResponse(subscriber);
        log.debug("Finished : querySubscriber().........." );
        log.debug("Returning response : querySubscriber().........." );
		return resp;

    }
 
}
