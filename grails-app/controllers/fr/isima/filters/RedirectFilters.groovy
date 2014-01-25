package fr.isima.filters

/**
 * Created by alexandre on 25/01/2014.
 */
class RedirectFilters {

    static final LAST_URL = 'lastUrl';

    def filters = {

        saveUrl(invert: true, controller: 'user|oauth') {
            before = {

                def targetURI = request.forwardURI - request.contextPath
                session[LAST_URL] = targetURI
                return true

            }
        }

    }

}
