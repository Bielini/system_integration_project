//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace ServiceReference1
{
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.3-preview3.21351.2")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://soap.api.bie.pl/", ConfigurationName="ServiceReference1.MyFirstSOAPInterface")]
    public interface MyFirstSOAPInterface
    {
        
        [System.ServiceModel.OperationContractAttribute(Action= "http://soap.api.bie.pl/SOAPInterface/getAllByNameRequest", ReplyAction= "http://soap.api.bie.pl/SOAPInterface/getAllByNameResponse")]
        [System.ServiceModel.DataContractFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        System.Threading.Tasks.Task<string> getAllByNameAsync(string arg0);
        
        [System.ServiceModel.OperationContractAttribute(Action= "http://soap.api.bie.pl/SOAPInterface/getSpecificCategoryDataRequest", ReplyAction= "http://soap.api.bie.pl/SOAPInterface/getSpecificCategoryDataResponse")]
        [System.ServiceModel.DataContractFormatAttribute(Style=System.ServiceModel.OperationFormatStyle.Rpc)]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        System.Threading.Tasks.Task<string> getSpecificCategoryDataAsync(string arg0);

        [System.ServiceModel.OperationContractAttribute(Action = "http://soap.api.bie.pl/SOAPInterface/getSpecificYearDataRequest", ReplyAction = "http://soap.api.bie.pl/SOAPInterface/getSpecificYearDataResponse")]
        [System.ServiceModel.DataContractFormatAttribute(Style = System.ServiceModel.OperationFormatStyle.Rpc)]
        [return: System.ServiceModel.MessageParameterAttribute(Name = "return")]
        System.Threading.Tasks.Task<string> getSpecificYearDataAsync(string arg0);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.3-preview3.21351.2")]
    public interface MyFirstSOAPInterfaceChannel : ServiceReference1.MyFirstSOAPInterface, System.ServiceModel.IClientChannel
    {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("Microsoft.Tools.ServiceModel.Svcutil", "2.0.3-preview3.21351.2")]
    public partial class SOAPInterfaceClient : System.ServiceModel.ClientBase<ServiceReference1.MyFirstSOAPInterface>, ServiceReference1.MyFirstSOAPInterface
    {
        
        /// <summary>
        /// Implement this partial method to configure the service endpoint.
        /// </summary>
        /// <param name="serviceEndpoint">The endpoint to configure</param>
        /// <param name="clientCredentials">The client credentials</param>
        static partial void ConfigureEndpoint(System.ServiceModel.Description.ServiceEndpoint serviceEndpoint, System.ServiceModel.Description.ClientCredentials clientCredentials);
        
        public SOAPInterfaceClient() : 
                base(SOAPInterfaceClient.GetDefaultBinding(), SOAPInterfaceClient.GetDefaultEndpointAddress())
        {
            this.Endpoint.Name = EndpointConfiguration.StatisticsEndpoint.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public SOAPInterfaceClient(EndpointConfiguration endpointConfiguration) : 
                base(SOAPInterfaceClient.GetBindingForEndpoint(endpointConfiguration), SOAPInterfaceClient.GetEndpointAddress(endpointConfiguration))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public SOAPInterfaceClient(EndpointConfiguration endpointConfiguration, string remoteAddress) : 
                base(SOAPInterfaceClient.GetBindingForEndpoint(endpointConfiguration), new System.ServiceModel.EndpointAddress(remoteAddress))
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public SOAPInterfaceClient(EndpointConfiguration endpointConfiguration, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(SOAPInterfaceClient.GetBindingForEndpoint(endpointConfiguration), remoteAddress)
        {
            this.Endpoint.Name = endpointConfiguration.ToString();
            ConfigureEndpoint(this.Endpoint, this.ClientCredentials);
        }
        
        public SOAPInterfaceClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress)
        {
        }
        
        public System.Threading.Tasks.Task<string> getAllByNameAsync(string arg0)
        {
            return base.Channel.getAllByNameAsync(arg0);
        }  
        public System.Threading.Tasks.Task<string> getSpecificYearDataAsync(string arg0)
        {
            return base.Channel.getSpecificYearDataAsync(arg0);
        }
        
        public System.Threading.Tasks.Task<string> getSpecificCategoryDataAsync(string arg0)
        {
            return base.Channel.getSpecificCategoryDataAsync(arg0);
        }
        
        public virtual System.Threading.Tasks.Task OpenAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginOpen(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndOpen));
        }
        
        public virtual System.Threading.Tasks.Task CloseAsync()
        {
            return System.Threading.Tasks.Task.Factory.FromAsync(((System.ServiceModel.ICommunicationObject)(this)).BeginClose(null, null), new System.Action<System.IAsyncResult>(((System.ServiceModel.ICommunicationObject)(this)).EndClose));
        }
        
        private static System.ServiceModel.Channels.Binding GetBindingForEndpoint(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.StatisticsEndpoint))
            {
                System.ServiceModel.BasicHttpBinding result = new System.ServiceModel.BasicHttpBinding();
                result.MaxBufferSize = int.MaxValue;
                result.ReaderQuotas = System.Xml.XmlDictionaryReaderQuotas.Max;
                result.MaxReceivedMessageSize = int.MaxValue;
                result.AllowCookies = true;
                return result;
            }
            throw new System.InvalidOperationException(string.Format("Could not find endpoint with name \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.EndpointAddress GetEndpointAddress(EndpointConfiguration endpointConfiguration)
        {
            if ((endpointConfiguration == EndpointConfiguration.StatisticsEndpoint))
            {
                return new System.ServiceModel.EndpointAddress("http://localhost:7779/statistics");
            }
            throw new System.InvalidOperationException(string.Format("Could not find endpoint with name \'{0}\'.", endpointConfiguration));
        }
        
        private static System.ServiceModel.Channels.Binding GetDefaultBinding()
        {
            return SOAPInterfaceClient.GetBindingForEndpoint(EndpointConfiguration.StatisticsEndpoint);
        }
        
        private static System.ServiceModel.EndpointAddress GetDefaultEndpointAddress()
        {
            return SOAPInterfaceClient.GetEndpointAddress(EndpointConfiguration.StatisticsEndpoint);
        }
        
        public enum EndpointConfiguration
        { 
            StatisticsEndpoint,
        }
    }
}
