import React, { useState } from "react";
import Logo from "../assets/images/logo.jpg";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [academicYear, setAcademicYear] = useState("");

  
  const [error, setError] = useState("");
  const [token, setToken] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) {
        throw new Error("Invalid username or password");
      }

      const data = await response.json();
      //console.log("JWT Token:", data.token);
      setToken(data.token);

      // ✅ Save token in localStorage for later requests
      localStorage.setItem("token", data.token);

      // TODO: Redirect to dashboard or another page
      // e.g., window.location.href = "/dashboard";

    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div
      className="min-h-screen flex items-center justify-center"
      style={{ backgroundColor: "#023561" }}
    >
      <div className="w-full max-w-md bg-white p-6 rounded-lg shadow-md">
        {/* Logo/Image */}
        <div className="flex justify-center mb-3">
          <img src={Logo} alt="Logo" className="w-30 h-30 object-contain" />
        </div>

        <h2 className="text-xl font-bold text-center mb-4 text-[#023561]">
          Login
        </h2>

        <form onSubmit={handleLogin} className="space-y-3">
          {/* Academic Year Dropdown */}
          <div>
            <label className="block text-gray-700 mb-1 text-sm">
              Academic Year
            </label>
            <select
              value={academicYear}
              onChange={(e) => setAcademicYear(e.target.value)}
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#023561]"
              required
            >
              <option value="" disabled>
                Select academic year
              </option>
              <option value="2025-26">2025-26</option>
              <option value="2024-25">2024-25</option>
              <option value="2023-24">2023-24</option>
            </select>
          </div>

          {/* Username */}
          <div>
            <label className="block text-gray-700 mb-1 text-sm">Username</label>
            <input
              type="text"
              placeholder="Enter your username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#023561]"
              required
            />
          </div>

          {/* Password */}
          <div>
            <label className="block text-gray-700 mb-1 text-sm">Password</label>
            <input
              type="password"
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-[#023561]"
              required
            />
          </div>

          {/* Error Message */}
          {error && (
            <div className="text-red-500 text-sm text-center">{error}</div>
          )}

          {/* Login Button */}
          <button
            type="submit"
            className="w-full bg-[#023561] hover:bg-[#021f3b] text-white py-2 rounded transition-colors"
          >
            Login
          </button>
        </form>

        {/* Forgot Password Link */}
        <div className="mt-3 text-center">
          <a href="#!" className="text-[#023561] hover:underline text-sm">
            Forgot Password?
          </a>
        </div>

        {/* Show token (for debugging) */}
        {token && (
          <div className="mt-3 p-2 text-xs break-all bg-gray-100 rounded">
            <strong>Token:</strong> {token}
          </div>
        )}
      </div>
    </div>
  );
};

export default Login;
