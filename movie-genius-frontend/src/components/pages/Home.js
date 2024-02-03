import React from "react";
import {Link} from "react-router-dom";
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';

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
                  <input className={"h-15 bg-opacity-30 bg-black rounded-md w-96 p-5 bg-"} placeholder={"Email address"}/>
                </div>
                <div className={"items-center"}>
                  <button className={"flex flex-row items-center bg-red-600 rounded-md h-16 py-2 px-6 text-white font-bold text-3xl"}>
                    <p>Get Started</p>
                    <div>
                      <ArrowForwardIcon sx={{
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
