import React from "react";
import {Link} from "react-router-dom";
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import "./Home.css";

const Home = () => {
  return (
    <div className={"flex flex-col h-screen"}>

      <nav className={"flex items-center justify-between p-5"}>
        <h1 className={"text-red-600 font-extrabold italic text-4xl"}>
          MovieGenius
        </h1>
        <div>
          <ul>
            <li></li>
            <li className={"italic"}>
              <Link to={"/login"} className={"bg-red-600 py-2 px-4 rounded-md text-white text-md font-bold"}>
                Sign In
              </Link>
            </li>
          </ul>
        </div>
       </nav>

      <main className={"flex-1 flex items-center justify-center text-center"}>
        <div className={"space-y-4"}>
          <div className={"space-y-5"}>
            <h1 className={"text-white font-black text-5xl italic"}>
              Find all the latest movie trailers
            </h1>
            <p className={"text-white text-2xl"}>From your favourite genres.</p>
          </div>
          <div className={"space-y-4"}>
            <div>
              <p className={"text-white text-2xl"}>
                Ready to watch? Enter your email here to create your account.
              </p>
            </div>
            <form>
              <div className={"flex justify-center items-center space-x-2"}>
                <div>
                  <div className="relative">
                    <input type="text" id="emailInput"
                           className="block px-3 pb-2.5
                                      pt-4 w-96 h-16 text-md text-white
                                      bg-opacity-30 bg-black rounded-md
                                      appearance-none duration-300
                                      focus:outline-none
                                      focus:ring-0 focus:border-2 focus:border-gray-100 peer"
                           placeholder=" "/>
                    <label htmlFor="emailInput"
                           className="absolute text-md text-gray-300 font-semibold bg-none
                                      duration-300 transform -translate-y-4 scale-75 top-5
                                      z-10 origin-[0] px-3
                                      peer-placeholder-shown:scale-100
                                      peer-placeholder-shown:-translate-y-3 peer-placeholder-shown:top-1/2
                                      peer-focus:top-5 peer-focus:scale-75 peer-focus:-translate-y-4
                                      start-1 rtl:peer-focus:translate-x-1/4 rtl:peer-focus:left-auto">
                      Email address
                    </label>
                  </div>
                </div>
                <div className={"items-center"}>
                  <button
                    className={"flex flex-row items-center bg-red-600 group active:bg-red-700 rounded-md h-16 py-2 px-6 text-white font-bold"}>
                    <p className={"group-active:text-gray-300 text-2xl"}>Get Started</p>
                    <div>
                      <ArrowForwardIcon className={"group-active:text-gray-300"} sx={{
                        fontSize: 24,
                        fontWeight: 'bold',
                        color: 'white',
                        lineHeight: 28,
                        marginLeft: 2,
                      }}/>
                    </div>
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </main>

      <footer className={"px-4 items-end justify-center text-center"}>
        <h2>Made by Tito Osemobor</h2>

      </footer>
    </div>
  );
};

export default Home;

