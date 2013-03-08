/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transport.nio.tcp.protocols;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * This Protocol will send the actual Mule Message over the TCP channel, and in this
 * way we are preserving any headers which might be needed, for example Correlation
 * IDs in order to be able to aggregate messages after chunking. Data are read until
 * the client closes the channel.
 */
public class MuleMessageEOFProtocol extends EOFProtocol
{

    @Override
    public Object read(final InputStream is) throws IOException
    {
        return MuleMessageWorker.doRead(super.read(is));
    }

    @Override
    public void write(final OutputStream os, final Object unused) throws IOException
    {
        super.write(os, MuleMessageWorker.doWrite());
    }

}