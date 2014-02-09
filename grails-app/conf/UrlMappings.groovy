class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index", controller: "home", action: "index")
		"500"(view:'/error')
        "404"(view:"/error404", controller: "home", action: "error404")
	}
}
