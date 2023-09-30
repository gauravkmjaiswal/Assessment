import React, { useEffect, useState } from 'react';
import WebSocket from 'websocket';
import "./style.css"

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
      <h2 className="crypto-title">Real Time Data Of Bitcoin to USDT Trades</h2>
  {data && (
    <table className="crypto-table">
                <tbody>
                      <tr>
                          <td>Symbol</td>
                          <td>{data.s}</td>
                      </tr>
                      <tr>
                          <td>Price</td>
                          <td>{data.p}</td>
                      </tr>
                      <tr>
                          <td>Time</td>
                          <td>{data.E}</td>
                      </tr>
                      <tr>
                          <td>Quantity</td>
                          <td>{data.q}</td>
                      </tr>
                      <tr>
                          <td>Buyer Id</td>
                          <td>{data.b}</td>
                      </tr>
                      <tr>
                          <td>Seller Id</td>
                          <td>{data.a}</td>
                      </tr>
                      <tr>
                          <td>Trade Time</td>
                          <td>{data.T}</td>
                      </tr>
                </tbody>
            </table>
      )}
      
    </div>
  );
};

export default RealTimeData;












