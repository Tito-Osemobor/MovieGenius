import React from "react";
import Footer from "../components/Footer";
import Input from "../components/common/Input";
import Button from "../components/common/Button";

const Login = () => {
  return (
    <div className={"flex flex-col h-screen"}>

      <nav className={"flex items-center justify-between p-5"}>
        <h1 className={"text-red-600 font-extrabold italic text-4xl"}>
          MovieGenius
        </h1>
      </nav>

      <main className={"flex-1 flex items-center justify-center text-center"}>
        <div className={"flex-1 bg-white"}>
          <h2 className={"text-black"}>Sign in</h2>
          <form>
            <Input placeholder={"Email address"} />
            <Input placeholder={"Password"} />
            <Button />
          </form>
        </div>
      </main>

      <Footer />
    </div>
  );
}

export default Login;
