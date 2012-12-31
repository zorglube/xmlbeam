/**
 *  Copyright 2012 Sven Ewald
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
package org.xmlbeam.tutorial.e03_eclipse;

import java.util.List;

import org.xmlbeam.URL;
import org.xmlbeam.Xpath;

/**
 * We proceed with our examples to parameterized projections. Because
 * projections will be compiled and processed when used, there is no need to
 * keep them static. Instead give your getter method some parameters. They will
 * be applied as a {@lik MessageFormat} on the Xpath expression. (This is
 * possible on URL annotations, too).
 * 
 * @author <a href="https://github.com/SvenEwald">Sven Ewald</a>
 */

//START SNIPPET: EclipseCodeFormatterConfig
@URL("resource://eclipsecodeformatprofile.xml")
public interface EclipseFormatterConfigFile {

    interface Setting {

        @Xpath("@id")
        String getName();

        @Xpath("@value")
        String getValue();
        
    }

    @Xpath(value = "//profile/@name", targetComponentType = String.class)
    List<String> getProfileNames();

    @Xpath(value = "//profiles/profile[@name=\"{0}\"]/setting", targetComponentType = Setting.class)
    List<Setting> getAllSettingsForProfile(String profileName);
    
}
//START SNIPPET: EclipseCodeFormatterConfig