import http from 'k6/http';
import { check } from 'k6';

export const options = {
  scenarios: {
    constant_100_rpm: {
      executor: 'constant-arrival-rate',
      rate: 100,            // Exactly 100 requests
      timeUnit: '1m',        // Per 1 minute (100 RPM)
      duration: '3m',        // Run the test for 3 minutes
      preAllocatedVUs: 10,   // Initial pool of virtual users
      maxVUs: 50,            // Max virtual users allowed if needed
    },
  },
  thresholds: {
    http_req_failed: ['rate<0.01'],   // Less than 1% errors allowed
    http_req_duration: ['p(95)<500'], // 95% of requests should respond in under 500ms
  },
};

export default function () {
  // 1. Target URL
  const url = 'http://localhost:8080/store-deposite';

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