/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.keyboardplaying.dailytasks;

import org.keyboardplaying.dailytasks.core.Application;
import org.keyboardplaying.dailytasks.core.events.ApplicationWindowGetter;

/**
 * Main class for the application.
 * <p/>
 * This class is a mindless launcher which will instantiate and start the application.
 *
 * @author Cyrille Chopelet (https://keyboardplaying.org)
 */
public final class Launcher {

    /**
     * Private constructor for utility class.
     */
    private Launcher() {
    }

    /**
     * Main method for the application.
     *
     * @param args optional arguments; first argument can be used to pass the path to the properties file
     */
    public static void main(String... args) {
        Application app = new Application();
        app.setWindowGetter(new ApplicationWindowGetter(app));
        app.start();
    }
}
