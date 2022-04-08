using ServiceReference1;
using System;
using System.Threading.Tasks;

namespace IS_Lab5_SOAPCS
{
    class Program
    {
        //averageSalary,massiveParties,carAccidents, deathsNumber
        static async Task Main(string[] args)
        {

            Console.WriteLine("SOAP Client!");
            SOAPInterfaceClient client = new SOAPInterfaceClient();

          //  string text = await client.getAllByNameAsync("Lubelskie");
          //  string text = await client.getSpecificCategoryDataAsync("averageSalary");
          
            string text = await client.getSpecificYearDataAsync("2015");


          

             Console.WriteLine(text);

        }




    }
    }

