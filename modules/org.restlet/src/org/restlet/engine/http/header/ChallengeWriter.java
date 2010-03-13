/**
 * Copyright 2005-2010 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.engine.http.header;

import org.restlet.data.ChallengeRequest;
import org.restlet.data.Parameter;

/**
 * Authentication challenge header writer.
 * 
 * @author Jerome Louvel
 */
public class ChallengeWriter extends HeaderWriter<ChallengeRequest> {

    /** Indicates if the first challenge parameter is written. */
    private volatile boolean firstChallengeParameter;

    /**
     * Constructor.
     */
    public ChallengeWriter() {
        this.firstChallengeParameter = true;
    }

    @Override
    public HeaderWriter<ChallengeRequest> append(ChallengeRequest value) {
        return this;
    }

    /**
     * Appends a new challenge parameter, prefixed with a comma. The value is
     * separated from the name by an '=' character.
     * 
     * @param parameter
     *            The parameter.
     * @return This writer.
     */
    public ChallengeWriter appendChallengeParameter(Parameter parameter) {
        return appendChallengeParameter(parameter.getName(), parameter
                .getValue());
    }

    /**
     * Appends a new parameter, prefixed with a comma.
     * 
     * @param name
     *            The parameter name.
     * @return The current builder.
     */
    public ChallengeWriter appendChallengeParameter(String name) {
        appendChallengeParameterSeparator();
        appendToken(name);
        return this;
    }

    /**
     * Appends a new parameter, prefixed with a comma. The value is separated
     * from the name by an '=' character.
     * 
     * @param name
     *            The parameter name.
     * @param value
     *            The parameter value.
     * @return The current builder.
     */
    public ChallengeWriter appendChallengeParameter(String name, String value) {
        appendChallengeParameterSeparator();

        if (name != null) {
            appendToken(name);
        }

        if (value != null) {
            append('=');
            appendToken(value);
        }

        return this;
    }

    /**
     * Appends a comma as a separator if the first parameter has already been
     * written.
     * 
     * @return The current builder.
     */
    public ChallengeWriter appendChallengeParameterSeparator() {
        if (isFirstChallengeParameter()) {
            setFirstChallengeParameter(false);
        } else {
            append(", ");
        }

        return this;
    }

    /**
     * Appends a new parameter, prefixed with a comma. The value is separated
     * from the name by an '=' character.
     * 
     * @param parameter
     *            The parameter.
     * @return The current builder.
     */
    public ChallengeWriter appendQuotedChallengeParameter(Parameter parameter) {
        return appendQuotedChallengeParameter(parameter.getName(), parameter
                .getValue());
    }

    /**
     * Appends a new parameter, prefixed with a comma. The value is quoted and
     * separated from the name by an '=' character.
     * 
     * @param name
     *            The parameter name.
     * @param value
     *            The parameter value to quote.
     * @return The current builder.
     */
    public ChallengeWriter appendQuotedChallengeParameter(String name,
            String value) {
        appendChallengeParameterSeparator();

        if (name != null) {
            appendToken(name);
        }

        if (value != null) {
            append('=');
            appendQuotedString(value);
        }

        return this;
    }

    /**
     * Indicates if the first comma-separated value is written.
     * 
     * @return True if the first comma-separated value is written.
     */
    public boolean isFirstChallengeParameter() {
        return firstChallengeParameter;
    }

    /**
     * Indicates if the first comma-separated value is written.
     * 
     * @param firstValue
     *            True if the first comma-separated value is written.
     */
    public void setFirstChallengeParameter(boolean firstValue) {
        this.firstChallengeParameter = firstValue;
    }
}
