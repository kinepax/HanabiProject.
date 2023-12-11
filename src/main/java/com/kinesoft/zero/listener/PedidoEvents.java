package com.kinesoft.zero.listener;

public class PedidoEvents {

    private EventsPedidoListener eventsPedidoListener;

    public void setEventsListener(EventsPedidoListener eventsPedidoListener) {
        this.eventsPedidoListener = eventsPedidoListener;
    }

    public void notifySaveDocumentoPago(int idDocumentoPagoCab){
        if(eventsPedidoListener !=null) {
            System.out.println("Soy el eevento de l listener del crud");
            eventsPedidoListener.notifySaveDocumentoPago(idDocumentoPagoCab);
        }
    }
    public void notifySavePedido(){
        if(eventsPedidoListener !=null) {
            System.out.println("Soy el eevento de l listener del crud");
            eventsPedidoListener.notifySavePedido();
        }
    }

}
