import { initMercadoPago, Wallet } from "@mercadopago/sdk-react";
import { useState, useEffect } from "react";

interface Props {
  preferenceId: string;
}

function CheckoutMP({ preferenceId }: Props) {
  const [isVisible, setIsVisible] = useState(false);

  useEffect(() => {
    initMercadoPago("TEST-34f56a1a-ee13-4cae-8593-d65a8e849b42", {
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
