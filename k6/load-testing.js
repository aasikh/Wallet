import http from 'k6/http';
import { check } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 50 },  // Ramp up to 50 requests/sec
    { duration: '1m', target: 200 },  // Ramp up to 200 requests/sec
    { duration: '30s', target: 0 },   // Ramp down
  ],
};

export default function () {
  // 1. Target URL
  const url = 'https://wallet-sr5e.onrender.com/deposite';

  // 2. Form payload matching your Wallet fields (e.g., amount, userId, currency)
  // Replace 'amount' or other field names with actual fields of your Wallet class!
  const payload = {
    amount: '100',
    // description: 'Test Deposit',
  };

  // 3. Send POST request
  const res = http.post(url, payload);

  // 4. Verify HTTP 200 response
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
}