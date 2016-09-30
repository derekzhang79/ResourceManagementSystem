/*-- 

 Copyright (C) 2000 Brett McLaughlin & Jason Hunter.
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.
 
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows 
    these conditions in the documentation and/or other materials 
    provided with the distribution.

 3. The name "JDOM" must not be used to endorse or promote products
    derived from this software without prior written permission.  For
    written permission, please contact license@jdom.org.
 
 4. Products derived from this software may not be called "JDOM", nor
    may "JDOM" appear in their name, without prior written permission
    from the JDOM Project Management (pm@jdom.org).
 
 In addition, we request (but do not require) that you include in the 
 end-user documentation provided with the redistribution and/or in the 
 software itself an acknowledgement equivalent to the following:
     "This product includes software developed by the
      JDOM Project (http://www.jdom.org/)."
 Alternatively, the acknowledgment may be graphical using the logos 
 available at http://www.jdom.org/images/logos.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE JDOM AUTHORS OR THE PROJECT
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 This software consists of voluntary contributions made by many 
 individuals on behalf of the JDOM Project and was originally 
 created by Brett McLaughlin <brett@jdom.org> and 
 Jason Hunter <jhunter@jdom.org>.  For more information on the 
 JDOM Project, please see <http://www.jdom.org/>.
 
 */

package com.gits.rms.xml.utils;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Filter for removing formatting from data- or field-oriented XML.
 * 
 * <i>Code and comments adapted from DataWriter-0.2, written by David Megginson
 * and released into the public domain, without warranty.</i>
 * 
 * <p>
 * This filter removes leading and trailing whitespace from field-oriented XML
 * without mixed content. Note that this class will likely not yield appropriate
 * results for document-oriented XML like XHTML pages, which mix character data
 * and elements together.
 * </p>
 * 
 * @see DataFormatFilter
 */
public class DataUnformatFilter extends XMLFilterBase {

    // //////////////////////////////////////////////////////////////////
    // Constructors.
    // //////////////////////////////////////////////////////////////////

    private static final Object SEEN_DATA = new Object();

    private static final Object SEEN_ELEMENT = new Object();

    // //////////////////////////////////////////////////////////////////
    // Public methods.
    // //////////////////////////////////////////////////////////////////

    private static final Object SEEN_NOTHING = new Object();

    // //////////////////////////////////////////////////////////////////
    // Methods from org.xml.sax.ContentHandler.
    // //////////////////////////////////////////////////////////////////

    private Object state = SEEN_NOTHING;

    private Stack stateStack = new Stack();

    private StringBuffer whitespace = new StringBuffer();

    /**
     * Create a new filter.
     */
    public DataUnformatFilter() {
    }

    /**
     * Create a new filter.
     * 
     * <p>
     * Use the XMLReader provided as the source of events.
     * </p>
     * 
     * @param xmlreader
     *            The parent in the filter chain.
     */
    public DataUnformatFilter(XMLReader xmlreader) {
        super(xmlreader);
    }

    /**
     * Filter a character data event.
     * 
     * @param ch
     *            The characters to write.
     * @param start
     *            The starting position in the array.
     * @param length
     *            The number of characters to use.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#characters
     */
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (this.state != SEEN_DATA) {

            /* Look for non-whitespace. */

            int end = start + length;
            while (end-- > start) {
                if (!this.isXMLWhitespace(ch[end])) {
                    break;
                }
            }

            /*
             * If all the characters are whitespace, save them for later. If
             * we've got some data, emit any saved whitespace and update our
             * state to show we've seen data.
             */

            if (end < start) {
                this.saveWhitespace(ch, start, length);
            } else {
                this.state = SEEN_DATA;
                this.emitWhitespace();
            }
        }

        /* Pass on everything inside a data field. */

        if (this.state == SEEN_DATA) {
            super.characters(ch, start, length);
        }
    }

    // //////////////////////////////////////////////////////////////////
    // Internal methods.
    // //////////////////////////////////////////////////////////////////

    /**
     * Discards saved whitespace.
     */
    protected void clearWhitespace() {
        this.whitespace.setLength(0);
    }

    /**
     * Passes saved whitespace down the filter chain.
     */
    protected void emitWhitespace() throws SAXException {
        char[] data = new char[this.whitespace.length()];
        this.whitespace.getChars(0, data.length, data, 0);
        this.whitespace.setLength(0);
        super.characters(data, 0, data.length);
    }

    /**
     * Filter an end element event.
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @param qName
     *            The element's qualified (prefixed) name.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#endElement
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (this.state == SEEN_ELEMENT) {
            this.clearWhitespace();
        } else {
            this.emitWhitespace();
        }
        this.state = this.stateStack.pop();
        super.endElement(uri, localName, qName);
    }

    /**
     * Filter an ignorable whitespace event.
     * 
     * @param ch
     *            The array of characters to write.
     * @param start
     *            The starting position in the array.
     * @param length
     *            The number of characters to write.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#ignorableWhitespace
     */
    @Override
    public void ignorableWhitespace(char ch[], int start, int length) throws SAXException {
        this.emitWhitespace();
        // ignore
    }

    // //////////////////////////////////////////////////////////////////
    // Constants.
    // //////////////////////////////////////////////////////////////////

    /**
     * Returns <var>true</var> if character is XML whitespace.
     */
    private boolean isXMLWhitespace(char c) {
        return (c == ' ') || (c == '\t') || (c == '\r') || (c == '\n');
    }

    /**
     * Filter a processing instruction event.
     * 
     * @param target
     *            The PI target.
     * @param data
     *            The PI data.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#processingInstruction
     */
    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        this.emitWhitespace();
        super.processingInstruction(target, data);
    }

    /**
     * Reset the filter so that it can be reused.
     * 
     * <p>
     * This method is especially useful if the filter failed with an exception
     * the last time through.
     * </p>
     */
    public void reset() {
        this.state = SEEN_NOTHING;
        this.stateStack = new Stack();
        this.whitespace = new StringBuffer();
    }

    // //////////////////////////////////////////////////////////////////
    // Internal state.
    // //////////////////////////////////////////////////////////////////

    /**
     * Saves trailing whitespace.
     */
    protected void saveWhitespace(char[] ch, int start, int length) {
        this.whitespace.append(ch, start, length);
    }

    /**
     * Filter a start document event.
     * 
     * <p>
     * Reset state and pass the event on for further processing.
     * </p>
     * 
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startDocument
     */
    @Override
    public void startDocument() throws SAXException {
        this.reset();
        super.startDocument();
    }

    /**
     * Filter a start element event.
     * 
     * @param uri
     *            The element's Namespace URI.
     * @param localName
     *            The element's local name.
     * @param qName
     *            The element's qualified (prefixed) name.
     * @param atts
     *            The element's attribute list.
     * @exception org.xml.sax.SAXException
     *                If a filter further down the chain raises an exception.
     * @see org.xml.sax.ContentHandler#startElement
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        this.clearWhitespace();
        this.stateStack.push(SEEN_ELEMENT);
        this.state = SEEN_NOTHING;
        super.startElement(uri, localName, qName, atts);
    }

}

// end of DataUnformatFilter.java
