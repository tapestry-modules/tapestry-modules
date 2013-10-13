tapestry-modules
================

Tapestry module that extracts documentation data from github, POM files etc.

Getting the list of modules

	public class GithubIndex {
			
		@Inject
		private GitHub gitHub;
	
		@Property
		private TapestryModule module;
		
		public List<TapestryModule>  getModules() {
			return new ArrayList<TapestryModule>(gitHub.getCachedModules().values());
		}
	
	}

	
Getting info about one module

	public class Module {
		
		@PageActivationContext
		@Property
		private String name;
		
		@Property
		private String html;
		
		@Property
		private TapestryModule module;
		
		@Property
		private Model model;
		
		@Inject
		private GitHub gitHub;
		
		@Inject
		private POM pom;
		
		@Inject
		private MarkdownService markdownService;
		
		@SetupRender
		void setupRender() throws IOException {
			module = gitHub.getModule(name);
			html = markdownService.getHtml(module.getDescription(), "markdown");
			
			if ( module.getPoms().containsKey("src")) {
				URL url = new URL(module.getPoms().get("src"));
		        BufferedReader in = new BufferedReader(
		        new InputStreamReader(url.openStream()));
		        
		        model = pom.getModel(in);
			}
			
		}
	
	}