using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;
using Microsoft.Azure.SpringCloud.Client;
using System.IO;
using System.Reflection;
using log4net;
using log4net.Config;

namespace hello_world
{
    public class Program
    {   
        private static readonly ILog _log = LogManager.GetLogger(MethodBase.GetCurrentMethod().DeclaringType);        

        public static void Main(string[] args)
        {
            var logRepository = LogManager.GetRepository(Assembly.GetEntryAssembly());

            XmlConfigurator.Configure(logRepository, new FileInfo("log4net.config")); 

            _log.Info("Begins...");            

            CreateHostBuilder(args).Build().Run();

            _log.Info("Ends...");            
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder =>
                {
                    webBuilder.UseStartup<Startup>();
                })
                .UseAzureSpringCloudService();
    }
}