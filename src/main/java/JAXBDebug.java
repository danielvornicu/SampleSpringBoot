//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2020.11.16 à 06:12:54 PM CET 
//

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import com.sei.generate.ClientTO;
import com.sei.generate.DeleteClientByIdRequest;
import com.sei.generate.DeleteClientByIdResponse;
import com.sei.generate.FetchClientByIdRequest;
import com.sei.generate.FetchClientByIdResponse;
import com.sei.generate.FetchClientsRequest;
import com.sei.generate.FetchClientsResponse;
import com.sei.generate.GetInfoRequest;
import com.sei.generate.GetInfoResponse;
import com.sei.generate.ObjectFactory;
import com.sei.generate.SaveClientRequest;
import com.sei.generate.SaveClientResponse;
import com.sei.generate.TransferObject;

public class JAXBDebug {


    public static JAXBContext createContext(ClassLoader classLoader)
        throws JAXBException
    {
        return JAXBContext.newInstance(FetchClientsRequest.class, FetchClientsResponse.class, ClientTO.class, FetchClientByIdRequest.class, FetchClientByIdResponse.class, SaveClientRequest.class, SaveClientResponse.class, DeleteClientByIdRequest.class, DeleteClientByIdResponse.class, TransferObject.class, GetInfoRequest.class, GetInfoResponse.class, ObjectFactory.class);
    }

}
