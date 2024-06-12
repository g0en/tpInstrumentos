import { initMercadoPago, Wallet } from "@mercadopago/sdk-react";
import { useState, useEffect } from "react";

interface Props {
  preferenceId: string;
}

function CheckoutMP({ preferenceId }: Props) {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    initMercadoPago("TEST-749c0c7b-de56-4f89-ab1d-7caca80541f1", {
      locale: "es-AR",
    });
    if (preferenceId && preferenceId !== "") {
      setIsVisible(true);
    } else {
      setIsVisible(false);
    }
  }, [preferenceId]);

  //redirectMode es optativo y puede ser self, blank o modal
  return (
    <div>
      <div className={isVisible ? "divVisible" : "divInvisible"}>
        <Wallet
          initialization={{ preferenceId: preferenceId, redirectMode: "blank" }}
          customization={{ texts: { valueProp: "smart_option" } }}
        />
      </div>
    </div>
  );
}
export default CheckoutMP;
