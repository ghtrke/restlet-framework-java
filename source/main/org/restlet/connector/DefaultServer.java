/*
 * Copyright 2005-2006 Noelios Consulting.
 *
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the "License").  You may not use this file except
 * in compliance with the License.
 *
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt
 * See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL
 * HEADER in each file and include the License file at
 * http://www.opensource.org/licenses/cddl1.txt
 * If applicable, add the following below this CDDL
 * HEADER, with the fields enclosed by brackets "[]"
 * replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package org.restlet.connector;

import java.io.IOException;

import org.restlet.Call;
import org.restlet.Factory;
import org.restlet.Restlet;
import org.restlet.component.Component;
import org.restlet.data.Protocol;

/**
 * Default server connector supporting multiples protocols.
 */
public class DefaultServer implements Server
{
   /** The wrapped server. */
   protected Server wrappedServer;
   
   /**
    * Constructor.
    * @param protocol The connector protocol.
    * @param name The unique connector name.
    * @param target The target Restlet.
    * @param port The listening port.
    */
   public DefaultServer(Protocol protocol, String name, Restlet target, int port)
   {
   	this.wrappedServer = Factory.getInstance().createServer(protocol, name, target, null, port);
   }
   
   /**
    * Constructor.
    * @param protocol The connector protocol.
    * @param name The unique connector name.
    * @param target The target Restlet.
    * @param address The optional listening IP address (useful if multiple IP addresses available).
    * @param port The listening port.
    */
   public DefaultServer(Protocol protocol, String name, Restlet target, String address, int port)
   {
   	this.wrappedServer = Factory.getInstance().createServer(protocol, name, target, address, port);
   }

   /**
    * Handles the HTTP protocol call.<br/>
    * The default behavior is to create an REST call and delegate it to the attached Restlet.
    * @param call The HTTP protocol call.
    */
   public void handle(ServerCall call) throws IOException
   {
   	this.wrappedServer.handle(call);
   }

   /**
    * Returns the target restlet.
    * @return The target restlet.
    */
   public Restlet getTarget()
   {
   	return this.wrappedServer.getTarget();
   }

   /**
    * Sets the target restlet.
    * @param target The target restlet.
    */
   public void setTarget(Restlet target)
   {
   	this.wrappedServer.setTarget(target);
   }

   /**
    * Configure the SSL properties for secure protocols like HTTPS.
    * @param keystorePath The path of the keystore file. 
    * @param keystorePassword The keystore password.
    * @param keyPassword The password of the server key .
    */
   public void configureSsl(String keystorePath, String keystorePassword, String keyPassword)
   {
   	this.wrappedServer.configureSsl(keystorePath, keystorePassword, keyPassword);
   }
   
   /** Starts the Restlet. */
   public void start() throws Exception
   {
   	this.wrappedServer.start();
   }

   /** Stops the Restlet. */
   public void stop() throws Exception
   {
   	this.wrappedServer.stop();
   }

   /**
    * Handles a call.
    * @param call The call to handle.
    */
   public void handle(Call call)
   {
   	this.wrappedServer.handle(call);
   }

   /**
    * Indicates if the Restlet is started.
    * @return True if the Restlet is started.
    */
   public boolean isStarted()
   {
   	return this.wrappedServer.isStarted();
   }

   /**
    * Indicates if the Restlet is stopped.
    * @return True if the Restlet is stopped.
    */
   public boolean isStopped()
   {
   	return this.wrappedServer.isStopped();
   }

   /**
    * Returns the parent component.
    * @return The parent component.
    */
   public Component getParent()
   {
   	return this.wrappedServer.getParent();
   }

   /**
    * Sets the parent component.
    * @param parent The parent component.
    */
   public void setParent(Component parent)
   {
   	this.wrappedServer.setParent(parent);   	
   }

   /**
    * Returns the connector's protocol.
    * @return The connector's protocol.
    */
   public Protocol getProtocol()
   {
   	return this.wrappedServer.getProtocol();
   }

   /**
    * Returns the name of this REST element.
    * @return The name of this REST element.
    */
   public String getName()
   {
   	return this.wrappedServer.getName();
   }

   /**
    * Returns the description of this REST element.
    * @return The description of this REST element.
    */
   public String getDescription()
   {
   	return this.wrappedServer.getDescription();
   }
   
}
