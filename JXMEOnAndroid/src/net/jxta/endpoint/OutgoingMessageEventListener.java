/*
 *
 * $Id: OutgoingMessageEventListener.java,v 1.1 2005/05/03 06:38:25 hamada Exp $
 *
 * Copyright (c) 2001 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *       Sun Microsystems, Inc. for Project JXTA."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA"
 *    must not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact Project JXTA at http://www.jxta.org.
 *
 * 5. Products derived from this software may not be called "JXTA",
 *    nor may "JXTA" appear in their name, without prior written
 *    permission of Sun.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SUN MICROSYSTEMS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of Project JXTA.  For more
 * information on Project JXTA, please see
 * <http://www.jxta.org/>.
 *
 * This license is based on the BSD license adopted by the Apache Foundation.
 */

package net.jxta.endpoint;

/**
 *  A listener for events concerning the status of outgoing messages.
 *
 */
public interface OutgoingMessageEventListener extends java.util.EventListener {

    /**
     *  The message identified by the event could not be sent. 
     *
     *  <p/>The cause of the failure, if any, is available from
     *  {@link OutgoingMessageEvent#getFailure()}. Failures while sending
     *  messages have several causes :
     *  
     *  <p/><ul>
     *      <li>An {@link java.io.IOException} means that the messenger cannot
     *      send the message and the messenger will accept no further messages
     *      to be sent.</li>
     *
     *      <li>A {@link java.lang.RuntimeException} mean that the message was
     *      not sent, but retries may or may not be possible based upon the
     *      status returned by {@link Messenger#isClosed()}.</li>
     *
     *      <li><code>null</code> means that the message was not sent, but may
     *      be retried. Usually the failure is due to local resource limits
     *      being exceeded. Attempts may be made to later resend the message,
     *      usually after waiting for congestion to clear.</li>
     *  </ul>
     */
    void messageSendFailed(OutgoingMessageEvent event);

    /**
     * The message identified by the event was successfully sent. Successfully 
     * sending a message is not a guarantee that it will be successfully
     * received by the destination.
     */
    void messageSendSucceeded(OutgoingMessageEvent event);

}
