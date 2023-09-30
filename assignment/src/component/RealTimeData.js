import React, { useEffect, useState } from 'react';
import WebSocket from 'websocket';


const RealTimeData = () => {
  const [data, setData] = useState(null);

  useEffect(() => {    
    
    const socket = new WebSocket.w3cwebsocket('wss://stream.binance.com:9443/ws/btcusdt@trade');

    socket.onopen = () => {
      console.log('WebSocket connection opened');
    };

    socket.onmessage = (message) => {
        console.log(message)
      setData(JSON.parse(message.data));
    };

    socket.onclose = () => {
      console.log('WebSocket connection closed');
    };

    return () => {
      socket.close();
    };
  }, []);

  return (
    <div>
      {data && (
        <div>
          <h2>Real Time Data Of Bitcoin to USDT Trades</h2>
          Trades
          <h5 >Symbol = {data.s}</h5>
          <h5 >Price = {data.p} </h5>
          <h5 >Time = {data.E} </h5>
          <h5 >Quantity = {data.q} </h5>
          <h5 >Buyer Id = {data.b} </h5>
          <h5 >Seller Id = {data.a} </h5>
          <h5 >Trade Time = {data.T} </h5>
        </div>
      )}
    </div>
  );
};

export default RealTimeData;












