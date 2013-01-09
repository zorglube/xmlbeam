/**
 *  Copyright 2013 Sven Ewald
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.xmlbeam.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.xmlbeam.XBProjector;
import org.xmlbeam.util.DOMHelper;
import org.xmlbeam.util.IOHelper;

/**
 * @author <a href="https://github.com/SvenEwald">Sven Ewald</a>
 */
public class XBUrlIO {

    private final XBProjector projector;
    private final Map<String,String> requestParams = new HashMap<String,String>();

    public XBUrlIO(XBProjector projector) {
        this.projector = projector;
        requestParams.put("Content-Type","text/xml");
    }

    /**
     * Create a new projection using a given uri parameter. When the uri starts with the protocol
     * identifier "resource://" the classloader of projection interface will be used to read the
     * resource from the current class path.
     * 
     * @param uri
     * @param projectionInterface
     *            A Java interface to project the data on.
     * @return a new projection instance.
     * @throws IOException
     */
    public <T> T getFromURL(final String uri, final Class<T> projectionInterface) throws IOException {
        Document document = DOMHelper.getDocumentFromURL(projector.config().getDocumentBuilder(), uri, requestParams, projectionInterface);
        return projector.projectXML(document, projectionInterface);
    }

    /**
     * Post the projected document to a http url. The response is provided as a raw string.
     * 
     * @param projection
     * @param httpurl
     * @return response as String
     * @throws IOException
     */
    public String postToURL(Object projection, String httpurl) throws IOException {          
        return IOHelper.inputStreamToString(IOHelper.httpPost(httpurl, projection.toString(), requestParams));
    }
    
    public XBUrlIO addRequestParams(Map<String,String> params) {
        requestParams.putAll(params);
        return this;
    }
       
    public XBUrlIO addRequestParam(String name,String value) {
        requestParams.put(name,value);
        return this;
    }
}