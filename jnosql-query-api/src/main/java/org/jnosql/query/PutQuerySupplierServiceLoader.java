/*
 *  Copyright (c) 2018 Otávio Santana and others
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *  You may elect to redistribute this code under either of these licenses.
 *  Contributors:
 *  Otavio Santana
 */

package org.jnosql.query;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

final class PutQuerySupplierServiceLoader {

    private static final List<PutQuerySupplier> LOADERS;

    static final Optional<PutQuerySupplier> INSTANCE;

    private static final String MESSAGE = "Could not found an implementation of PutQuerySupplier in service loader.";

    static {
        ServiceLoader<PutQuerySupplier> serviceLoader = ServiceLoader.load(PutQuerySupplier.class);
        LOADERS = StreamSupport.stream(serviceLoader.spliterator(), false).collect(toList());
        INSTANCE = LOADERS.stream().findFirst();
    }

   static PutQuerySupplier getInstance() {
        return INSTANCE.orElseThrow(() -> new IllegalStateException(MESSAGE));
   }
}
